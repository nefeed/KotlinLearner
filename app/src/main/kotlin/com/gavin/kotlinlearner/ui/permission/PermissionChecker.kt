package com.gavin.kotlinlearner.ui.permission

import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-05-10
 * Time: 15:25
 */
class PermissionChecker(context: Context) {
    private var mContext: Context = context

    // 判断权限集合
    fun lacksPermissions(permissions: Array<String>): Boolean {
        for (permission in permissions) {
            if (lacksPermission(permission)) {
                return true
            }
        }
        return false
    }

    // 判断是否缺少权限
    private fun lacksPermission(permission: String): Boolean = ContextCompat.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_DENIED
}