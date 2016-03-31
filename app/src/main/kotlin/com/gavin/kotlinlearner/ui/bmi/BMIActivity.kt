package com.gavin.kotlinlearner.ui.bmi

import android.os.Bundle
import com.gavin.kotlinlearner.R.string.*
import com.gavin.kotlinlearner.app.Const
import com.gavin.kotlinlearner.ui.base.BaseActivity
import org.jetbrains.anko.*

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-03-21
 * Time: 18:34
 */
class BMIActivity : BaseActivity() {

    override var TAG: String = this.javaClass.simpleName

    val MAN_LOWER_BMI : Float = 20.7f
    val MAN_UPPER_BMI : Float = 26.4f
    val WOMAN_LOWER_BMI : Float = 19.1f
    val WOMAN_UPPER_BMI : Float = 25.8f

    var mBmi : Float = 0f
    var mAdvice : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BmiUi().setContentView(this)

        setTitle(bmi_title)

    }

    fun calculateBmi(ui: AnkoContext<BMIActivity>, height: Float, weight: Float, sex: Int) : CharSequence {

        ui.async() {
            Thread.sleep(1200)

            activityUiThreadWithContext {
                toast("感谢使用测量BMI功能！")
            }
        }

        mBmi = weight / (height * height)

        when (sex) {
            Const.SEX_MAN -> {
                if (mBmi < MAN_LOWER_BMI) {
                    mAdvice = String.format(getString(bmi_lower), mBmi)
                } else if (mBmi > MAN_UPPER_BMI) {
                    mAdvice = String.format(getString(bmi_upper), mBmi)
                } else {
                    mAdvice = String.format(getString(bmi_normal), mBmi)
                }
            }
            Const.SEX_WOMAN -> {
                if (mBmi < WOMAN_LOWER_BMI) {
                    mAdvice = String.format(getString(bmi_lower), mBmi)
                } else if (mBmi > WOMAN_UPPER_BMI) {
                    mAdvice = String.format(getString(bmi_upper), mBmi)
                } else {
                    mAdvice = String.format(getString(bmi_normal), mBmi)
                }
            }
            else -> {
                mAdvice = getString(unknown_wrong)
            }
        }

        return mAdvice
    }

}
