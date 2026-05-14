package com.sadiq.arogyasahayalocal

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.sadiq.arogyasahayalocal.data.local.AppDatabase
import com.sadiq.arogyasahayalocal.data.repository.HealthCampRepository
import com.sadiq.arogyasahayalocal.data.repository.MedicineRepository
import com.sadiq.arogyasahayalocal.data.repository.ProfileRepository
import com.sadiq.arogyasahayalocal.data.repository.UserRepository
import com.sadiq.arogyasahayalocal.data.repository.VitalLogRepository
import com.sadiq.arogyasahayalocal.notification.NotificationHelper
import com.sadiq.arogyasahayalocal.ui.screens.AddMedicineScreen
import com.sadiq.arogyasahayalocal.ui.screens.AshaConnectScreen
import com.sadiq.arogyasahayalocal.ui.screens.DashboardScreen
import com.sadiq.arogyasahayalocal.ui.screens.EmergencySOSScreen
import com.sadiq.arogyasahayalocal.ui.screens.LoginScreen
import com.sadiq.arogyasahayalocal.ui.screens.MedicalProfileScreen
import com.sadiq.arogyasahayalocal.ui.screens.MedicineHistoryScreen
import com.sadiq.arogyasahayalocal.ui.screens.RegisterScreen
import com.sadiq.arogyasahayalocal.ui.screens.SplashScreen
import com.sadiq.arogyasahayalocal.ui.screens.UpdatePasswordScreen
import com.sadiq.arogyasahayalocal.ui.screens.VitalGraphScreen
import com.sadiq.arogyasahayalocal.ui.screens.VitalLogScreen
import com.sadiq.arogyasahayalocal.ui.theme.ArogyaSahayaLocalTheme
import com.sadiq.arogyasahayalocal.viewmodel.HealthCampViewModel
import com.sadiq.arogyasahayalocal.viewmodel.HealthCampViewModelFactory
import com.sadiq.arogyasahayalocal.viewmodel.MedicineViewModel
import com.sadiq.arogyasahayalocal.viewmodel.MedicineViewModelFactory
import com.sadiq.arogyasahayalocal.viewmodel.ProfileViewModel
import com.sadiq.arogyasahayalocal.viewmodel.ProfileViewModelFactory
import com.sadiq.arogyasahayalocal.viewmodel.UserViewModel
import com.sadiq.arogyasahayalocal.viewmodel.UserViewModelFactory
import com.sadiq.arogyasahayalocal.viewmodel.VitalLogViewModel
import com.sadiq.arogyasahayalocal.viewmodel.VitalLogViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        --------------------------------
        WEEKLY LOGIN SESSION (7 DAYS)
        --------------------------------
        */

        val sharedPreferences = getSharedPreferences(
            "user_session",
            Context.MODE_PRIVATE
        )

        val lastLoginTime = sharedPreferences.getLong(
            "last_login_time",
            0L
        )

        val currentTime = System.currentTimeMillis()

        val sevenDaysInMillis =
            7 * 24 * 60 * 60 * 1000L

        val shouldAutoLogin =
            (currentTime - lastLoginTime) < sevenDaysInMillis

        /*
        --------------------------------
        NOTIFICATION CHANNEL
        --------------------------------
        */

        NotificationHelper.createNotificationChannel(this)

        /*
        --------------------------------
        ROOM DATABASE
        --------------------------------
        */

        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "arogya_database"
        )
            .fallbackToDestructiveMigration()
            .build()

        /*
        --------------------------------
        REPOSITORIES
        --------------------------------
        */

        val medicineRepository =
            MedicineRepository(
                database.medicineDao()
            )

        val vitalRepository =
            VitalLogRepository(
                database.vitalLogDao()
            )

        val userRepository =
            UserRepository(
                database.userDao()
            )

        val profileRepository =
            ProfileRepository(
                database.profileDao()
            )

        val healthCampRepository =
            HealthCampRepository(
                database.healthCampDao()
            )

        /*
        --------------------------------
        UI START
        --------------------------------
        */

        setContent {

            /*
            --------------------------------
            VIEWMODELS
            --------------------------------
            */

            val medicineViewModel: MedicineViewModel =
                viewModel(
                    factory = MedicineViewModelFactory(
                        medicineRepository
                    )
                )

            val vitalViewModel: VitalLogViewModel =
                viewModel(
                    factory = VitalLogViewModelFactory(
                        vitalRepository
                    )
                )

            val userViewModel: UserViewModel =
                viewModel(
                    factory = UserViewModelFactory(
                        userRepository
                    )
                )

            val profileViewModel: ProfileViewModel =
                viewModel(
                    factory = ProfileViewModelFactory(
                        profileRepository
                    )
                )

            val healthCampViewModel: HealthCampViewModel =
                viewModel(
                    factory = HealthCampViewModelFactory(
                        healthCampRepository
                    )
                )

            /*
            --------------------------------
            SPLASH FIRST
            --------------------------------
            */

            var currentScreen by remember {
                mutableStateOf("splash")
            }

            ArogyaSahayaLocalTheme {

                when (currentScreen) {

                    /*
                    --------------------------------
                    SPLASH SCREEN
                    --------------------------------
                    */

                    "splash" -> SplashScreen(
                        onSplashFinished = {
                            currentScreen =
                                if (shouldAutoLogin)
                                    "dashboard"
                                else
                                    "login"
                        }
                    )

                    /*
                    --------------------------------
                    LOGIN SCREEN
                    --------------------------------
                    */

                    "login" -> LoginScreen(
                        viewModel = userViewModel,

                        onRegisterClick = {
                            currentScreen = "register"
                        },

                        onLoginSuccess = {
                            sharedPreferences.edit()
                                .putLong(
                                    "last_login_time",
                                    System.currentTimeMillis()
                                )
                                .apply()

                            currentScreen = "dashboard"
                        }
                    )

                    /*
                    --------------------------------
                    REGISTER SCREEN
                    --------------------------------
                    */

                    "register" -> RegisterScreen(
                        viewModel = userViewModel,

                        onLoginClick = {
                            currentScreen = "login"
                        }
                    )

                    /*
                    --------------------------------
                    DASHBOARD
                    --------------------------------
                    */

                    "dashboard" -> DashboardScreen(

                        onAddMedicineClick = {
                            currentScreen = "addMedicine"
                        },

                        onHistoryClick = {
                            currentScreen = "history"
                        },

                        onVitalLogClick = {
                            currentScreen = "vitalLog"
                        },

                        onGraphClick = {
                            currentScreen = "graph"
                        },

                        onProfileClick = {
                            currentScreen = "profile"
                        },

                        onSOSClick = {
                            currentScreen = "sos"
                        },

                        onAshaClick = {
                            currentScreen = "asha"
                        },

                        onUpdatePasswordClick = {
                            currentScreen = "updatePassword"
                        },

                        onLogoutClick = {
                            sharedPreferences.edit()
                                .clear()
                                .apply()

                            currentScreen = "login"
                        }
                    )

                    /*
                    --------------------------------
                    ADD MEDICINE
                    --------------------------------
                    */

                    "addMedicine" -> AddMedicineScreen(
                        viewModel = medicineViewModel,
                        onBackClick = {
                            currentScreen = "dashboard"
                        }
                    )

                    /*
                    --------------------------------
                    MEDICINE HISTORY
                    --------------------------------
                    */

                    "history" -> MedicineHistoryScreen(
                        viewModel = medicineViewModel,
                        onBackClick = {
                            currentScreen = "dashboard"
                        }
                    )

                    /*
                    --------------------------------
                    VITAL LOG
                    --------------------------------
                    */

                    "vitalLog" -> VitalLogScreen(
                        viewModel = vitalViewModel,
                        onBackClick = {
                            currentScreen = "dashboard"
                        }
                    )

                    /*
                    --------------------------------
                    VITAL GRAPH
                    --------------------------------
                    */

                    "graph" -> VitalGraphScreen(
                        viewModel = vitalViewModel,
                        onBackClick = {
                            currentScreen = "dashboard"
                        }
                    )

                    /*
                    --------------------------------
                    MEDICAL PROFILE
                    --------------------------------
                    */

                    "profile" -> MedicalProfileScreen(
                        viewModel = profileViewModel,
                        onBackClick = {
                            currentScreen = "dashboard"
                        }
                    )

                    /*
                    --------------------------------
                    EMERGENCY SOS
                    --------------------------------
                    */

                    "sos" -> EmergencySOSScreen(
                        viewModel = profileViewModel,
                        onBackClick = {
                            currentScreen = "dashboard"
                        }
                    )

                    /*
                    --------------------------------
                    ASHA CONNECT
                    --------------------------------
                    */

                    "asha" -> AshaConnectScreen(
                        viewModel = healthCampViewModel,
                        onBackClick = {
                            currentScreen = "dashboard"
                        }
                    )

                    /*
                    --------------------------------
                    UPDATE PASSWORD
                    --------------------------------
                    */

                    "updatePassword" -> UpdatePasswordScreen(
                        viewModel = userViewModel,
                        onBackClick = {
                            currentScreen = "dashboard"
                        }
                    )
                }
            }
        }
    }
}