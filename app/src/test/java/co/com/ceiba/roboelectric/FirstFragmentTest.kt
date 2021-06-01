package co.com.ceiba.roboelectric

import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE, sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class FirstFragmentTest {
    @Test
    fun clickOnButton_shouldGoneAText(){
        // Given
        val fragmentArguments = bundleOf()
        val scenario = FragmentScenario.launchInContainer(FirstFragment::class.java, fragmentArguments)
        val expected = View.VISIBLE
        var actual = -1
        // When
        scenario.onFragment{
            val view = it.view
            view?.findViewById<Button>(R.id.button_three)?.performClick()
            actual = view!!.findViewById<TextView>(R.id.textView_fragment_visibleOnClick).visibility
        }
        // Then
        assert(actual == expected)
    }
}