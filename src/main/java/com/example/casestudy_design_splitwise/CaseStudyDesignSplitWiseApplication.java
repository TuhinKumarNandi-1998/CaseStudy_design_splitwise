package com.example.casestudy_design_splitwise;

import com.example.casestudy_design_splitwise.Commands.RegisterUserCommand;
import com.example.casestudy_design_splitwise.Commands.Registry.RegisterCommand;
import com.example.casestudy_design_splitwise.Commands.Registry.RegistryCommandImplementation;
import com.example.casestudy_design_splitwise.Commands.UpdateProfileCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Scanner;
@EnableJpaAuditing
@SpringBootApplication
public class CaseStudyDesignSplitWiseApplication implements CommandLineRunner {

    private RegisterCommand registerCommand;
    private RegisterUserCommand registerUserCommand;
    private UpdateProfileCommand updateProfileCommand;
    public static Scanner sc;

    @Autowired
    public CaseStudyDesignSplitWiseApplication(RegisterCommand registerCommand,
                                               RegisterUserCommand registerUserCommand,
                                               UpdateProfileCommand updateProfileCommand) {
        this.registerCommand = registerCommand;
        this.registerUserCommand = registerUserCommand;
        this.updateProfileCommand = updateProfileCommand;
    }

    public static void main(String[] args) {
        SpringApplication.run(CaseStudyDesignSplitWiseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        sc = new Scanner(System.in);

        registerCommand.addCommand(registerUserCommand);
        registerCommand.addCommand(updateProfileCommand);

        String input = "4 UpdateProfile koel@2008";
        registerCommand.execute(input);
    }
}
