package com.example.login_practice

import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var account1: String? = null
    private var passward1: String? = null
    private var account2: String? = null
    private var passward2: String? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        检验是否已有用户信息（账号：1， 密码：1）
        passward1 = ykShareperfress.getInstance(this).getPassword()
        account1 = ykShareperfress.getInstance(this).getAccount()
        Log.v("yk","$passward1")
        Log.v("yk","$account1")

//        监测动画
        editText2.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                var animation = ObjectAnimator.ofFloat(left_arm,"rotation",0f,90f)
                animation.duration = 700
                left_arm.pivotX = left_arm.width.toFloat()
                left_arm.pivotY = 0F
                animation.start()

                var animation2 = ObjectAnimator.ofFloat(right_arm,"rotation",0f,-90f)
                animation2.duration = 700
                right_arm.pivotX = 0F
                right_arm.pivotY = 0F
                animation2.start()

                var animation3 = ObjectAnimator.ofFloat(left_hand,"translationY",0f,40f)
                animation3.duration = 200
                animation3.start()

                var animation4 = ObjectAnimator.ofFloat(right_hand,"translationY",0f,40f)
                animation4.duration = 200
                animation4.start()
            } else {
                var animation = ObjectAnimator.ofFloat(left_arm,"rotation",90f,0f)
                animation.duration = 400
                left_arm.pivotX = left_arm.width.toFloat()
                left_arm.pivotY = 0F
                animation.start()

                var animation2 = ObjectAnimator.ofFloat(right_arm,"rotation",-90f,0f)
                animation2.duration = 400
                right_arm.pivotX = 0F
                right_arm.pivotY = 0F
                animation2.start()

                var animation3 = ObjectAnimator.ofFloat(left_hand,"translationY",40f,0f)
                animation3.duration = 700
                animation3.start()

                var animation4 = ObjectAnimator.ofFloat(right_hand,"translationY",40f,0f)
                animation4.duration = 700
                animation4.start()
            }
        }

//        监控输入
       detectInput(editText1,editText2)

        login_button.setOnClickListener{
            Log.v("yk","login点击")
            if (account2 != null){
                if (passward2 != null){
                    Log.v("yk","账号 $account2")
                    Log.v("yk","密码 $passward2")
                    if (account1 == null){
//                        账号为空,重新设置
                        ykShareperfress.getInstance(this).saveAccount(account2!!)
                        ykShareperfress.getInstance(this).savePassward(passward2!!)
                        account1 = account2
                        showExitDialog01("注册成功")
                    }else if (account2 == account1){
                        if (passward2 != passward1){
                            showExitDialog01("密码错误")
                        }else{
                            showExitDialog01("登录成功")
                        }
                    }else{
                        showExitDialog01("该账号不存在")
                    }
                }else{
                    Toast.makeText(this,"请输入密码", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"请输入账号", Toast.LENGTH_SHORT).show()
            }
        }

    }

//    监测输入
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun detectInput(editView1: EditText,editView2: EditText){
      if(editView1 == editText1){
//          账号
          editView1.addTextChangedListener(object : ykTextWacher(){
              override fun afterTextChanged(p0: Editable?) {
                    if (p0.toString().isNotEmpty()){
                        account2 = p0.toString()
                    }
                  if (p0.toString().isEmpty()){
                      account2 = null
                  }
                  Log.v("yk","$account2")
              }
          })
      }
      if (editView2 == editText2){
//        密码
          editView2.addTextChangedListener(object : ykTextWacher(){
                override fun afterTextChanged(p0: Editable?) {
                    if (p0.toString().isNotEmpty()){
                        passward2 = p0.toString()
                    }else{
                        passward2 = null
                    }
                    Log.v("yk","密码：$passward2")

                }
            })
      }
    }

    private fun showExitDialog01(string: String) {
        AlertDialog.Builder(this)
//            .setTitle("标题")
            .setMessage(string)
            .setPositiveButton("确定", null)
            .show()
    }
}
