package com.example.casestudy_design_splitwise.Controllers;

import com.example.casestudy_design_splitwise.Exceptions.GroupNotFoundExceptions;
import com.example.casestudy_design_splitwise.Exceptions.ThisUserNotCreatedTheGroup;
import com.example.casestudy_design_splitwise.Exceptions.UserNotFoundException;
import com.example.casestudy_design_splitwise.Models.Group;
import com.example.casestudy_design_splitwise.Models.User;
import com.example.casestudy_design_splitwise.Repositories.GroupRepository;
import com.example.casestudy_design_splitwise.Repositories.UserRepository;
import com.example.casestudy_design_splitwise.Services.SettleUpService.SettleUpService;
import com.example.casestudy_design_splitwise.Services.SettleUpService.Transactions;
import com.example.casestudy_design_splitwise.dtos.SettleUpGroupRequestDTO;
import com.example.casestudy_design_splitwise.dtos.SettleUpGroupResponseDTO;
import com.example.casestudy_design_splitwise.dtos.SettleUpUserRequestDTO;
import com.example.casestudy_design_splitwise.dtos.SettleUpUserRespondDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class SettleUpController {
    private SettleUpService settleUpService;
    private GroupRepository groupRepository;

    @Autowired
    public SettleUpController(SettleUpService settleUpService,
                              GroupRepository groupRepository) {
        this.settleUpService = settleUpService;
        this.groupRepository = groupRepository;
    }

    public SettleUpUserRespondDTO settleUpUser(SettleUpUserRequestDTO requestDTO) throws UserNotFoundException {
        SettleUpUserRespondDTO settleUpUserRespondDTO = new SettleUpUserRespondDTO();
        List<Transactions> transactions = settleUpService.settleUpUser(requestDTO.getUserId());

        settleUpUserRespondDTO.setTransactions(transactions);
        return settleUpUserRespondDTO;
    }

    public SettleUpGroupResponseDTO settleUpGroup(SettleUpGroupRequestDTO requestDTO) throws GroupNotFoundExceptions, ThisUserNotCreatedTheGroup {
        SettleUpGroupResponseDTO responseDTO = new SettleUpGroupResponseDTO();
        //let's do the authentication for the valid userID here itself
        Optional<Group> optionalGroup = groupRepository.findById(requestDTO.getGroupID());
        if (optionalGroup.isEmpty()) {
            throw new GroupNotFoundExceptions("Group with id "+requestDTO.getGroupID() +" not found");
        }
        if(optionalGroup.get().getCreatedBy().getId() != requestDTO.getUserID()) {
            throw new ThisUserNotCreatedTheGroup("This is cannot settleUp the group");
        }
        List<Transactions> transactions = settleUpService.settleUpGroup(requestDTO.getGroupID());
        responseDTO.setTransactions(transactions);
        return responseDTO;
    }
}
