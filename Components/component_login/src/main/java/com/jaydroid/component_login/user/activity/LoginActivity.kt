package com.jaydroid.component_login.user.activity

import android.text.SpannableString
import android.text.TextUtils
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.jaydroid.component_login.R
import com.jaydroid.component_login.user.contract.LoginContract
import com.jaydroid.component_login.user.presenter.LoginPresenter
import com.jaydroid.conponent_base.app.AppConfig
import com.jaydroid.conponent_base.arouter.ARouterHelper
import com.jaydroid.conponent_base.arouter.ARouterHelper.Path.REGISTER_ACTIVITY_PATH
import com.jaydroid.conponent_base.base.mvp.BaseMVPActivity
import com.jaydroid.conponent_base.network.bean.wan.User
import com.jaydroid.conponent_base.widget.ClearEditText
import com.jaydroid.conponent_base.widget.LoginView

@Route(path = ARouterHelper.Path.LOGIN_ACTIVITY_PATH)
class LoginActivity : BaseMVPActivity<LoginContract.View, LoginPresenter>(),
    LoginContract.View, View.OnClickListener {

    private lateinit var usernameEditText: ClearEditText
    private lateinit var passwordEditText: ClearEditText
    private lateinit var loginView: LoginView
    private lateinit var registerTxtView: TextView
    private lateinit var closeImgView: ImageView

    override fun getLayoutResId(): Int {
        return R.layout.activity_login
    }

    override fun createPresenter(): LoginPresenter {
        return LoginPresenter()
    }

    override fun initView() {
        closeImgView = findViewById(R.id.iv_login_close)
        usernameEditText = findViewById(R.id.cet_login_username)
        passwordEditText = findViewById(R.id.cet_login_password)
        loginView = findViewById(R.id.lv_login)
        registerTxtView = findViewById(R.id.tv_user_register)

        //todo debug
        usernameEditText.setText("18369679781")
        passwordEditText.setText("a11111111")
    }

    override fun initData() {
        super.initData()
        val spannableString = SpannableString(registerTxtView.text.toString().trim())
        spannableString.setSpan(
            UnderlineSpan(),
            0,
            registerTxtView.text.toString().trim().length,
            SpannableString.SPAN_INCLUSIVE_EXCLUSIVE
        )
        registerTxtView.text = spannableString
        registerTxtView.setOnClickListener(this)
        loginView.setOnClickListener(this)
        closeImgView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.lv_login -> {
                login()
            }
            R.id.tv_user_register -> {
                ARouterHelper.routerTo(REGISTER_ACTIVITY_PATH)
            }
            R.id.iv_login_close -> {
                finish()
            }
        }
    }

    private fun login() {
        val username = usernameEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(mContext, "请输入用户名", Toast.LENGTH_LONG).show()
            return
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(mContext, "请输入密码", Toast.LENGTH_LONG).show()
            return
        }
        presenter.login(username, password)
    }


    override fun showLoading() {
        loginView.setState(LoginView.STATE_LOADING)
    }

    override fun dismissLoading() {
        loginView.setState(LoginView.STATE_FAILED)
    }

    override fun onLoginResult(username: String, user: User?) {
        val pm = this.packageManager
        val appName = applicationInfo.loadLabel(pm).toString()
        ARouterHelper.routerTo(ARouterHelper.Path.HOME_ACTIVITY_PATH)
        finish()
    }


    override fun onDestroy() {
        super.onDestroy()
        loginView.release()
    }
}
