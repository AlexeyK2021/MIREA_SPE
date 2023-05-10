package ru.eco.automan.viewModels

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

/**
 * Класс перечисления для хранения настроек приложений.
 */
enum class SettingsTypes(name: String) {
    THEME("THEME"),
    LANGUAGE("LANGUAGE"),
    NOTIFICATIONS("NOTIFICATIONS")
}

/**
 * ViewModel, отвечающая за работу с настройками приложения
 * @param sharedPreferences системный класс для хранения настроек приложения
 */
class SettingsViewModel(private val sharedPreferences: SharedPreferences) : ViewModel() {
    private val editPrefs = sharedPreferences.edit()

    /**
     * Метод настройки уведомлений
     * @param b установка пользователя.
     */
    fun setNotifications(b: Boolean) {
        editPrefs.putBoolean(SettingsTypes.NOTIFICATIONS.name, b)
        editPrefs.commit()
    }

    /**
     * Метод настройки темы приложения
     * @param themeId ID-номер темы, установка пользователя
     */
    fun setTheme(themeId: Int) {
        editPrefs.putInt(SettingsTypes.THEME.name, themeId)
        editPrefs.commit()
    }

    /**
     * Метод настройки языка в приложении
     * @param languageId ID-номер языка, установка пользователя
     */
    fun setLanguage(languageId: Int) {
        editPrefs.putInt(SettingsTypes.LANGUAGE.name, languageId)
        editPrefs.commit()
    }

    /**
     * Метод получения текущего статуса по уведомлениям
     * @return текущий статус уведомлений
     */
    fun getNotificationStatus(): Boolean =
        sharedPreferences.getBoolean(SettingsTypes.NOTIFICATIONS.name, true)

    /**
     * Метод получения текущего статуса по теме
     * @return текущий ID-номер темы приложения
     */
    fun getThemePref(): Int = sharedPreferences.getInt(SettingsTypes.THEME.name, 0)

    /**
     * Метод получения текущего статуса по языку
     * @return текущий ID-номер языка приложения
     */
    fun getLanguagePref(): Int = sharedPreferences.getInt(SettingsTypes.LANGUAGE.name, 0)

}