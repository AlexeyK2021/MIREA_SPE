package ru.eco.automan.viewModels

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

enum class SettingsTypes(name: String) {
    THEME("THEME"),
    LANGUAGE("LANGUAGE"),
    NOTIFICATIONS("NOTIFICATIONS")
}

class SettingsViewModel(private val sharedPreferences: SharedPreferences) : ViewModel() {
    private val editPrefs = sharedPreferences.edit()
//    private val settings
//        get() =

    fun setNotifications(b: Boolean) {
        editPrefs.putBoolean(SettingsTypes.NOTIFICATIONS.name, b)
        editPrefs.commit()
    }

    fun setTheme(themeId: Int) {
        editPrefs.putInt(SettingsTypes.THEME.name, themeId)
        editPrefs.commit()
    }

    fun setLanguage(languageId: Int) {
        editPrefs.putInt(SettingsTypes.LANGUAGE.name, languageId)
        editPrefs.commit()
    }

    fun getNotificationStatus(): Boolean =
        sharedPreferences.getBoolean(SettingsTypes.NOTIFICATIONS.name, true)

    fun getThemePref(): Int = sharedPreferences.getInt(SettingsTypes.THEME.name, 0)
    fun getLanguagePref(): Int = sharedPreferences.getInt(SettingsTypes.LANGUAGE.name, 0)

}