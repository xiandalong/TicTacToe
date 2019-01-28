package com.my.tictacteo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class TicTacToeActivity extends AppCompatActivity implements TicTacToeContracts.View {

    private TicTacToeContracts.Presenter presenter;

    TextView dashboardText;
    ImageView currentPlayerIcon;
    Map<String, Integer> boardItemMap = new HashMap<String, Integer>() {{
        put("0_0", R.id.button_0_0);
        put("0_1", R.id.button_0_1);
        put("0_2", R.id.button_0_2);
        put("0_3", R.id.button_0_3);
        put("1_0", R.id.button_1_0);
        put("1_1", R.id.button_1_1);
        put("1_2", R.id.button_1_2);
        put("1_3", R.id.button_1_3);
        put("2_0", R.id.button_2_0);
        put("2_1", R.id.button_2_1);
        put("2_2", R.id.button_2_2);
        put("2_3", R.id.button_2_3);
        put("3_0", R.id.button_3_0);
        put("3_1", R.id.button_3_1);
        put("3_2", R.id.button_3_2);
        put("3_3", R.id.button_3_3);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_title);

        dashboardText = findViewById(R.id.dash_board_text);
        currentPlayerIcon = findViewById(R.id.current_player_icon);

        presenter = new TicTacToePresenter(this, 4);
    }

    public void onBoardItemClicked(View view) {
        ImageButton button = (ImageButton) view;
        String buttonIdName = getResources().getResourceEntryName(button.getId());
        String[] parts = buttonIdName.split("_");
        int row = Integer.valueOf(parts[1]);
        int col = Integer.valueOf(parts[2]);
        presenter.onBoardItemClicked(row, col);

    }

    public void onResetClicked(View view) {
        presenter.onResetClicked();
    }

    @Override
    public void updateClickedItem(int row, int col, int player) {
        ImageButton button = findViewById(boardItemMap.get(row + "_" + col));
        button.setImageResource(getPlayerIconResource(player));
    }

    @Override
    public void clearBoard() {
        for (int id : boardItemMap.values()) {
            ImageView view = findViewById(id);
            view.setImageDrawable(null);
        }
    }

    @Override
    public void showCurrentPlayer(int player) {
        dashboardText.setText(getString(R.string.current_player));
        currentPlayerIcon.setVisibility(View.VISIBLE);
        currentPlayerIcon.setImageResource(getPlayerIconResource(player));
    }

    private int getPlayerIconResource(int player) {
        return player == 1 ? R.drawable.circle : R.drawable.cross;
    }

    @Override
    public void showPlayerWon(int player) {
        dashboardText.setText(getString(R.string.player_won, player));
        currentPlayerIcon.setVisibility(View.GONE);
    }

    @Override
    public void showTie() {
        dashboardText.setText(getString(R.string.its_a_tie));
        currentPlayerIcon.setVisibility(View.GONE);
    }
}
