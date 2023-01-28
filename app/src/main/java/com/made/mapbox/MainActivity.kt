package com.made.mapbox

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions

class MainActivity : AppCompatActivity() {

    companion object {
        private const val ICON_ID = "ICON_ID"
    }

    private lateinit var mapView: MapView
    private lateinit var btnNavigation: Button
    private lateinit var mapboxMap: MapboxMap
    private lateinit var symbolManager: SymbolManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.mapView)
        btnNavigation = findViewById(R.id.btnNavigation)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { mapboxMap ->
            this.mapboxMap = mapboxMap
            mapboxMap.setStyle(Style.MAPBOX_STREETS) { style ->
                symbolManager = SymbolManager(mapView, mapboxMap, style)
                symbolManager.iconAllowOverlap = true

                style.addImage(
                    ICON_ID,
                    BitmapFactory.decodeResource(resources, com.mapbox.mapboxsdk.R.drawable.mapbox_marker_icon_default)
                )

                showLocation()
            }
        }
    }

    private fun showLocation() {
        val location = LatLng(-6.8957643, 107.6338462)
        symbolManager.create(
            SymbolOptions()
                .withLatLng(LatLng(location.latitude, location.longitude))
                .withIconImage(ICON_ID)
                .withIconSize(1.5f)
                .withIconOffset(arrayOf(0f, -1.5f))
                .withTextField("Location")
                .withTextHaloColor("rgba(255, 255, 255, 100)")
                .withTextHaloWidth(5.0f)
                .withTextAnchor("top")
                .withTextOffset(arrayOf(0f, 1.5f))
                .withDraggable(true)
        )

        mapboxMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 8.0))
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}