package com.example.cs408_lab_1a_rock_paper_scissors;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.cs408_lab_1a_rock_paper_scissors.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Weapon playerWeapon, computerWeapon;
    private int numPlayerWins = 0, numComputerWins = 0;
    private Weapon[] weapons = Weapon.values();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void computerChoosesWeapon() {
        Random rand = new Random();
        int randWeaponIndex = rand.nextInt(weapons.length);
        computerWeapon = Weapon.values()[randWeaponIndex];
    }

    public void showWeaponReport() {
        StringBuilder report = new StringBuilder();
        report.append(getResources().getString(R.string.players_weapon_report)).append(" ").append(playerWeapon).append("\n");
        report.append(getResources().getString(R.string.computers_weapon_report)).append(" ").append(computerWeapon);
        binding.weaponReport.setText(report);
    }

    // Sets custom text, adds to game record, updates game record message
    public void determineWinner() {
        // Tie
        if (playerWeapon == computerWeapon) {
            binding.gameWinner.setText(R.string.tie_game);
        }
        else if (playerWeapon == Weapon.ROCK) {
            // Computer wins: paper wraps rock
            if (computerWeapon == Weapon.PAPER) {
                binding.gameWinner.setText(getResources().getString(R.string.computer_wins) + getResources().getString(R.string.paper_beats_rock));
                numComputerWins++;
            }
            // Player wins: rock breaks scissors
            else if (computerWeapon == Weapon.SCISSORS) {
                binding.gameWinner.setText(getResources().getString(R.string.player_wins) + getResources().getString(R.string.rock_beats_scissors));
                numPlayerWins++;
            }
        }
        else if (playerWeapon == Weapon.PAPER) {
            // Player wins: paper wraps rock
            if (computerWeapon == Weapon.ROCK) {
                binding.gameWinner.setText(getResources().getString(R.string.player_wins) + getResources().getString(R.string.paper_beats_rock));
                numPlayerWins++;
            }
            // Computer wins: Scissors cut paper
            else if (computerWeapon == Weapon.SCISSORS) {
                binding.gameWinner.setText(getResources().getString(R.string.computer_wins) + getResources().getString(R.string.scissors_beats_paper));
                numComputerWins++;
            }
        }
        else if (playerWeapon == Weapon.SCISSORS) {
            // Computer wins: Rock breaks scissors
            if (computerWeapon == Weapon.ROCK) {
                binding.gameWinner.setText(getResources().getString(R.string.computer_wins) + getResources().getString(R.string.rock_beats_scissors));
                numComputerWins++;
            }
            // Player wins: Scissors cut paper
            else if (computerWeapon == Weapon.PAPER) {
                binding.gameWinner.setText(getResources().getString(R.string.player_wins) + getResources().getString(R.string.scissors_beats_paper));
                numPlayerWins++;
            }
        }
        updateGameRecord();
    }

    private void updateGameRecord() {
        binding.gameRecord.setText("Player: " + numPlayerWins + ", Computer: " + numComputerWins);
    }


    public void onClickRock(View v) {
        playerWeapon = Weapon.ROCK;
        computerChoosesWeapon();
        showWeaponReport();
        determineWinner();
    }
    public void onClickPaper(View v) {
        playerWeapon = Weapon.PAPER;
        computerChoosesWeapon();
        showWeaponReport();
        determineWinner();
    }
    public void onClickScissors(View v) {
        playerWeapon = Weapon.SCISSORS;
        computerChoosesWeapon();
        showWeaponReport();
        determineWinner();
    }
}