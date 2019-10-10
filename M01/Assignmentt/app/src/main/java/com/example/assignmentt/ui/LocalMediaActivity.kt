package com.example.assignmentt.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.assignmentt.R
import com.example.assignmentt.VideoResources
import kotlinx.android.synthetic.main.activity_local_media.*

class LocalMediaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_media)

        onCreateImplementation()
    }

    override fun onStart() {
        super.onStart()

        onStartImplementation()

    }

    override fun onStop() {
        super.onStop()

        onStopImplementation()
    }

    // Lifecycle implementation
    private fun onCreateImplementation() {

        play_pause_button.isEnabled = false

        playOrPauseFunctionality()

        seekBarChangeFunctionality()

    }
    private fun onStartImplementation() {
        setVideo()
        syncSeekBarWithVideoFunctionality()
    }
    private fun onStopImplementation() {
        local_video_view.pause()
    }
    // Functionality
    private fun syncSeekBarWithVideoFunctionality() {
        local_video_view.setOnPreparedListener { mp ->
            play_pause_button.isEnabled = true
            mp?.let {
                local_video_seekbar.max = mp.duration
            }
        }
    }
    private fun setVideo() {
        local_video_view.setVideoURI(Uri.parse(VideoResources.getLocalVideoURI(packageName)))
    }
    private fun playOrPauseFunctionality() {
        play_pause_button.setOnClickListener {
            when(local_video_view.isPlaying) {
                false -> {
                    local_video_view.start()
                    play_pause_button.setImageDrawable(getDrawable(R.drawable.ic_pause_black_24dp))
                }
                true -> {
                    local_video_view.pause()
                    play_pause_button.setImageDrawable(getDrawable(R.drawable.ic_play_arrow_black_24dp))
                }
            }
        }
    }
    private fun seekBarChangeFunctionality() {
        local_video_seekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                seekBar?.let {
                    local_video_view.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}
