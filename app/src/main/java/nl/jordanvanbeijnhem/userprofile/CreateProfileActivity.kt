package nl.jordanvanbeijnhem.userprofile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_create_profile.*
import nl.jordanvanbeijnhem.userprofile.model.Profile

const val GALLERY_REQUEST_CODE = 100

class CreateProfileActivity : AppCompatActivity() {

    private var profileImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)
        initViews()
    }

    private fun initViews() {
        btnGallery.setOnClickListener { onGalleryClick() }
        btnConfirm.setOnClickListener { onConfirmClick() }
    }

    private fun onGalleryClick() {
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "image/*"
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
    }

    private fun onConfirmClick() {
        val profile = Profile(
            etFirstName.text.toString(),
            etLastName.text.toString(),
            etDescription.text.toString(),
            profileImageUri
        )

        val profileActivityIntent = Intent(this, ProfileActivity::class.java)
        profileActivityIntent.putExtra(PROFILE_EXTRA, profile)
        startActivity(profileActivityIntent)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                GALLERY_REQUEST_CODE -> {
                    profileImageUri = data?.data
                    ivProfileImage.setImageURI(profileImageUri)
                }
            }
        }
    }
}
