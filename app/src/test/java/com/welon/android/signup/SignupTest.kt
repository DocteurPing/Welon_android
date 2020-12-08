package com.welon.android.signup

import com.welon.android.utils.EnumReturnCheckPassword
import org.junit.Assert
import org.junit.Test

class SignupTest {
    private val activity = SignupActivity()
    private val presenter = SignupPresenter(activity)

    @Test
    fun checkNotMatchingPassword() {
        Assert.assertEquals(presenter.checkPassword("test", ""), EnumReturnCheckPassword.NOT_MATCHING)
    }

    @Test
    fun checkInvalidPassword() {
        Assert.assertEquals(presenter.checkPassword("test", "test"), EnumReturnCheckPassword.INVALID)
    }

    @Test
    fun checkGoodPassword() {
        Assert.assertEquals(presenter.checkPassword("Testtest1", "Testtest1"), EnumReturnCheckPassword.GOOD)
    }
}