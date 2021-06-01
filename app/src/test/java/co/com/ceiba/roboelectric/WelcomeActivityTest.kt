package co.com.ceiba.roboelectric

import android.app.Application
import android.content.Intent
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows
import org.robolectric.annotation.Config

@Config(manifest= Config.NONE, sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class WelcomeActivityTest {


    @Deprecated("ejemplo de método de prueba deprecado",
        ReplaceWith("clickingLoginUpdatedImplementation_shouldStartLoginActivity"))
    @Test
    fun clickingLogin_shouldStartLoginActivity(){
        // Given
        val activity = Robolectric.setupActivity(WelcomeActivity::class.java)
        val expectedIntent = Intent(activity, LoginActivity::class.java)

        // When
        activity.findViewById<FloatingActionButton>(R.id.fab).performClick()
        val actual = Shadows.shadowOf(RuntimeEnvironment.application).nextStartedActivity

        // Then
        assert(expectedIntent.component == actual.component)
    }

    @Test
    fun clickingLoginUpdatedImplementation_shouldStartLoginActivity(){
        // Given
        val scenario = ActivityScenario.launch(WelcomeActivity::class.java)
        var expectedIntent = Intent()

        // When
        scenario.onActivity {
            expectedIntent = Intent(it, LoginActivity::class.java)
            it.findViewById<FloatingActionButton>(R.id.fab).performClick()
        }
        val application: Application = ApplicationProvider.getApplicationContext()
        val actual = Shadows.shadowOf(application).nextStartedActivity

        // Then
        assert(expectedIntent.component == actual.component)
    }

    @Test
    fun clickingButtonTwo_shouldAppearButton(){
        // Given
        val scenario = ActivityScenario.launch(WelcomeActivity::class.java)
        var expectedText = View.VISIBLE
        var actual = -1

        // When
        scenario.onActivity {

            it.findViewById<Button>(R.id.button_two).performClick()
            actual = it.findViewById<TextView>(R.id.textView_visibleOnClick).visibility
        }

        // Then
        assert(expectedText == actual)
    }
}