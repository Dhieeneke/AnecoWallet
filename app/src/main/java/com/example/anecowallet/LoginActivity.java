package com.example.anecowallet;

import android.text.TextUtils;
    public class LoginActivity extends MainActivity {

        private EditText mEmailField;
        private EditText mPasswordField;

        private FirebaseAuth mAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            mEmailField = findViewById(R.id.emailField);
            mPasswordField = findViewById(R.id.passwordField);

            mAuth = FirebaseAuth.getInstance();

            Button loginButton = findViewById(R.id.loginButton);
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email = mEmailField.getText().toString();
                    String password = mPasswordField.getText().toString();

                    if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                        Toast.makeText(LoginActivity.this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                    } else {
                        mAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            Toast.makeText(LoginActivity.this, "Аутентификация прошла успешно.",
                                                    Toast.LENGTH_SHORT).show();
                                            // Redirect the user to the main activity
                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Toast.makeText(LoginActivity.this, "Ошибка аутентификации.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            });
            //Код ниже с chatGPT, но я не очень понял его сути, высылаю тебе. Посмотри и вырази мнение, если что подкорректируй пожалуйста
            public class LoginActivity extends AppCompatActivity {

                private EditText mEmail;
                private EditText mPassword;
                private Button mLoginButton;

                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_login);

                    mEmail = (EditText) findViewById(R.id.email);
                    mPassword = (EditText) findViewById(R.id.password);
                    mLoginButton = (Button) findViewById(R.id.login_button);

                    mLoginButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String email = mEmail.getText().toString().trim();
                            String password = mPassword.getText().toString().trim();

                            if (TextUtils.isEmpty(email)) {
                                mEmail.setError("Please enter your email");
                                return;
                            }

                            if (TextUtils.isEmpty(password)) {
                                mPassword.setError("Please enter your password");
                                return;
                            }

                            // Проверяем email и пароль пользователя на соответствие в БД
                            if (checkUserCredentials(email, password)) {
                                // Пользователь авторизован, переходим на главный экран
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // Пользователь не авторизован, выводим сообщение об ошибке
                                Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                // Функция для проверки email и пароля пользователя
                private boolean checkUserCredentials(String email, String password) {
                    // Выполняем запрос к БД и проверяем email и пароль
                    // Если email и пароль совпадают, возвращаем true, иначе false
                    return true;
                }
            }
        }
    }
