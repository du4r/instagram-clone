package com.du4r.instagramclone.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.du4r.instagramclone.common.base.DependencyInjector
import com.du4r.instagramclone.common.util.TxtWatcher
import com.du4r.instagramclone.databinding.ActivityLoginBinding
import com.du4r.instagramclone.login.Login
import com.du4r.instagramclone.login.presentation.LoginPresenter
import com.du4r.instagramclone.main.view.MainActivity
import com.du4r.instagramclone.register.view.RegisterActivity


//na activity herdamos a interface login.view para implementar seus metodos
class LoginActivity : AppCompatActivity(), Login.View {
    // criamos o binding para fazer uso do viewbinding
    // e o presenter que ira coordenar algumas acoes a serem realizadas com os dados passados pelo usuario
    private lateinit var binding: ActivityLoginBinding
    override lateinit var presenter: Login.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inflando o layout e configurando o viewbinding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //instanciando o presenter da activity
        presenter = LoginPresenter(this,DependencyInjector.loginRepository())

        // usei o metodo with para modernizar a forma como utiliza-se o viewbinding
        // very cool :)
        with(binding) {
            //antes de aplicar a logica adicionamos um evento para observar se o
            // o campo de email esta sendo modificado
            // o primeiro watcher verifica se os campos estao vazios ou nao
            loginEditEmail.addTextChangedListener(watcher)
            // ja o segundo chama o display emailFailure() informando que o erro de email e null no momento
            loginEditEmail.addTextChangedListener(TxtWatcher{
                displayEmailFailure(null)
            })
            // aplicamos a mesma logica usada no watcher do loginEditEmail no campo de senha
            loginEditPassword.addTextChangedListener(watcher)
            loginEditPassword.addTextChangedListener(
                TxtWatcher{displayPasswordFailure(null )} )

            //adicionamos um listener para o evento de clique do botao de entrar
            loginBtnEnter.setOnClickListener{
                //chamando o presenter.login que ira entregar ao presenter os dados do formulario em formato de strings
                presenter.login(
                    loginEditEmail.text.toString(),
                    loginEditPassword.text.toString())
            }
            //aqui fizemos um listener para que o clique no campo de cadastre-se fosse escutado
            // e levasse a funcao gotoRegisterScreen
            loginTxtRegister.setOnClickListener {
                goToRegisterScreen()
            }
        }
    }
    // essa funcao leva o usuario a activity de registro
    private fun goToRegisterScreen() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
    // essa funcao chama o metodo presenter.onDestroy ao fim da activity atual
    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
    // essa funcao é um observador dos campos de email e senha, serve para verificar se estao vazios ou nao
    private val watcher = TxtWatcher{ it ->
        binding.loginBtnEnter.isEnabled = binding.loginEditEmail.text.toString().isNotEmpty()
                && binding.loginEditPassword.text.toString().isNotEmpty()
    }
    // essa funcao ativa a progressBar dentro do botao customizado,
    // dando ao usuario a nocao de que os dados estao sendo processados
    override fun showProgress(enabled: Boolean) {
        binding.loginBtnEnter.showProgress(enabled)
    }
    // essa funcao envia um erro ao usuario proximo ao campo de email
    override fun displayEmailFailure(emailError: Int?) {
        binding.loginEditEmailInput.error = emailError?.let { getString(it) }
    }
    // essa funcao envia um erro ao usuario proximo ao campo de senha
    override fun displayPasswordFailure(passwordError: Int?) {
        binding.loginEditPasswordInput.error = passwordError?.let { getString(it) }
    }
    // essa funcao abre a activityMain quando chamada,
    // depende primeiramente se o usuario foi autenticado pelo database ou nao
    override fun onUserAuthenticated() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    // essa funcao imprime um erro ao usuario caso o login de erro
    override fun onUserUnauthorized(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG)
    }
}