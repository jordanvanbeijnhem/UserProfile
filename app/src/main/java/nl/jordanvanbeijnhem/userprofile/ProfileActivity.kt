package nl.jordanvanbeijnhem.userprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_profile.*
import nl.jordanvanbeijnhem.userprofile.model.Profile

const val PROFILE_EXTRA = "PROFILE_EXTRA"

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initViews()
    }

    private fun initViews() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "This is your profile!"

        val profile = intent.getParcelableExtra<Profile>(PROFILE_EXTRA)

        if (profile != null) {
            tvName.text = getString(R.string.name, profile.firstName, profile.lastName)
            tvDescription.text = profile.description
            if (profile.imageUri != null) {
                ivProfileImage.setImageURI(profile.imageUri)
            } else {
                ivProfileImage.setImageDrawable(getDrawable(R.drawable.ic_account_box_black_24dp))
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
