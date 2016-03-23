package com.gavin.kotlinlearner.ui.bmi

import android.graphics.Color
import android.text.Html
import android.text.InputType.TYPE_CLASS_NUMBER
import android.view.Gravity
import android.widget.*
import com.gavin.kotlinlearner.R
import com.gavin.kotlinlearner.R.string.*
import com.gavin.kotlinlearner.app.Const
import com.gavin.kotlinlearner.widget.bmiLogoView
import org.jetbrains.anko.*

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-03-22
 * Time: 11:27
 */
class BmiUi : AnkoComponent<BMIActivity> {

    var mTvAdvice :TextView ?= null
    var mEtHeight :EditText ?= null
    var mEtWeight :EditText ?= null
    var mRgSex :RadioGroup ?= null
    var mRbSexMan :RadioButton ?= null
    var mRbSexWoMan :RadioButton ?= null
    var mSex :Int = -1

    private val customeStyle = { v: Any ->
        when (v) {
            is Button -> v.textSize = 20f;
            is EditText -> v.textSize = 14f
            is TextView -> v.textSize = 14f;
            is RadioButton -> v.textSize = 14f;
        }
    }

    override fun createView(ui: AnkoContext<BMIActivity>) = with(ui) {
        verticalLayout {
            padding = dip(16)

            bmiLogoView()

            linearLayout {

                matchParent

                textView {
                    textResource = bmi_height
                }

                mEtHeight = editText {
                    hintResource = bmi_hint_height
                    inputType = TYPE_CLASS_NUMBER
                }.lparams {
                    height = wrapContent
                    width = matchParent
                }

            }.lparams {
                height = wrapContent
                width = matchParent
            }

            linearLayout {

                matchParent

                textView {
                    textResource = bmi_weight
                }

                mEtWeight = editText {
                    hintResource = bmi_hint_weight
                    inputType = TYPE_CLASS_NUMBER
                }.lparams {
                    height = wrapContent
                    width = matchParent
                }

            }.lparams {
                height = wrapContent
                width = matchParent
            }

            mRgSex = radioGroup {

                bottomPadding = dip(10)

                orientation = RadioGroup.HORIZONTAL

                mRbSexWoMan = radioButton {
                    textResource = sex_woman
                }

                mRbSexMan = radioButton {
                    textResource = sex_man
                }

                onCheckedChange { radioGroup, i ->
//                    mSex = i - 1
//                    println("系统默认选中了：" + mSex)
                    if ((mRbSexMan as RadioButton).isChecked) {
                        mSex = Const.SEX_MAN.ordinal
                    } else if ((mRbSexWoMan as RadioButton).isChecked) {
                        mSex = Const.SEX_WOMAN.ordinal
                    } else {
                        mSex = -1
                    }
//                    println("您选中：" + mSex)
                }

            }.lparams {
                height = wrapContent
                width = wrapContent
                gravity = Gravity.CENTER
            }

            button(bmi_calculate) {
                textColor = Color.WHITE;
                backgroundResource = R.color.colorPrimary

                onClick {
                    if (mEtHeight!!.text.toString().equals("")) {
                        toast("请先输入您要测量的身高")
                        mEtHeight!!.requestFocus()
                    } else if ( mEtWeight!!.text.toString().equals("")) {
                        toast("请先输入您要测量的体重")
                        mEtWeight!!.requestFocus()
                    } else if (mSex == -1) {
                        toast("请先选择您测量对象的性别")
                    } else {
                        var _height :Float = (mEtHeight?.text.toString().toFloat() / 100)
                        var _weight :Float = mEtWeight?.text.toString().toFloat()
                        var _advice = ui.owner.calculateBmi(ui, _height, _weight, mSex)
                        mTvAdvice?.text = Html.fromHtml(_advice.toString())
                        mEtHeight!!.clearFocus()
                        mEtWeight!!.clearFocus()
                    }
                }
            }

            mTvAdvice = textView {
                padding = dip(10)
                gravity = Gravity.CENTER_HORIZONTAL
                textResource = bmi_advice
            }

        }
    }.style( customeStyle )

}