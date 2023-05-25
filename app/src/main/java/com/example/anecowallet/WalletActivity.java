package com.example.anecowallet;

    public class WalletActivity extends AppCompatActivity {
            private CryptoAPI cryptoAPI;
            private String userId;
            private TextView balanceTextView;
            private EditText depositEditText;
            private Button depositButton;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_balance);
                userId = getIntent().getStringExtra("userId");
                cryptoAPI = new CryptoAPI(getIntent().getStringExtra("apiKey"));

                balanceTextView = findViewById(R.id.balanceTextView);
                depositEditText = findViewById(R.id.depositEditText);
                depositButton = findViewById(R.id.depositButton);
                depositButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String amountString = depositEditText.getText().toString();
                        double amount = Double.parseDouble(amountString);
                        boolean success = cryptoAPI.deposit(userId, amount);
                        if (success) {
                            Toast.makeText(BalanceActivity.this, "Депозит успешно внесен", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(BalanceActivity.this, "Депозит не внесен", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            protected void onResume() {
                super.onResume();
                double balance = cryptoAPI.getBalance(userId);
                balanceTextView.setText(String.format("%.2f", balance));
            }
        }
    }