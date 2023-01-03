package gm.books.app.presentation.viewmodels

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class DetailsViewModel @Inject constructor() : ViewModel() {

    var currentSlide: Int? = null
}