package com.example.dragonsofmugloar;

import com.example.dragonsofmugloar.services.GameRunner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class DragonsofmugloarApplication implements CommandLineRunner {

    private final GameRunner gameRunner;

    public static void main(String[] args) {
        SpringApplication.run(DragonsofmugloarApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        boolean playAgain;

        do {
            gameRunner.playGame();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Do you want to play again? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();

            playAgain = input.equals("y");
        } while (playAgain);

        log.info("Exiting the game. Goodbye!");
        System.exit(0);
    }
}
