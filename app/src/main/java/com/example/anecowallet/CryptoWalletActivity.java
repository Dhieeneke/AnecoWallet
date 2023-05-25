package com.example.anecowallet;

public class CryptoWalletActivity extends AppCompatActivity {

    private WalletAppKit kit; // Криптокошелек
    private Wallet wallet; // Биткоин кошелек
    private String receiveAddress; // Адрес получателя
    private BtcFormat btcFormat; // Формат отображения Bitcoin

    // Метод инициализации активности
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_wallet);

        // Инициализация криптокошелька
        kit = new WalletAppKit(TestNet3Params.get(), getFilesDir(), "wallet");

        // Добавление слушателя на изменение баланса
        kit.wallet().addCoinsReceivedEventListener(new WalletCoinsReceivedEventListener() {
            @Override
            public void onCoinsReceived(Wallet w, Transaction tx, Coin prevBalance, Coin newBalance) {
                // Актуализация информации о балансе криптокошелька
                updateBalance();
            }
        });

        // Сохранение криптокошелька
        kit.setAutoSave(true);

        // Запуск синхронизации кошелька с блокчейном
        kit.startAsync();

        // Получение биткоин кошелька
        wallet = kit.wallet();

        // Формат отображения Bitcoin
        btcFormat = BtcFormat.getInstance();

        // Получение адреса получателя
        receiveAddress = "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa";
    }

    // Метод обновления информации о балансе криптокошелька
    private void updateBalance() {
        // Получение баланса
        Coin balance = wallet.getBalance();

        // Отображение баланса на экране
        TextView balanceTextView = (TextView) findViewById(R.id.balance_text_view);
        balanceTextView.setText(btcFormat.format(balance));
    }

    // Метод отправки транзакции
    private void sendTransaction() {
        // Получение списка выходов (адресов, кому отправляется)
        List<Receiver> receivers = List.of(new Receiver(Payment.parse(receiveAddress), Coin.valueOf(50000)));

        // Создание объекта запроса на отправку транзакции
        SendRequest request = SendRequest.to(receivers);

        // Подписание и отправка транзакции
        wallet.completeTx(request);

        // Отображение сообщения об успешной отправке транзакции
        Toast.makeText(this, "Операция выполнена успешно!", Toast.LENGTH_SHORT).show();
    }
}