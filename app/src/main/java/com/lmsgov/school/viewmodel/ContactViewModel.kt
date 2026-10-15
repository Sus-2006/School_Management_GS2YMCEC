package com.lmsgov.school.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmsgov.school.data.model.ContactFormData
import com.lmsgov.school.data.model.ContactFormResponse
import com.lmsgov.school.data.model.SchoolContactInfo
import com.lmsgov.school.data.repository.LmsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ContactViewModel : ViewModel() {
    private val repository = LmsRepository()

    var contactInfo by mutableStateOf<SchoolContactInfo?>(null)
        private set
    var isLoading by mutableStateOf(true)
        private set
    var formName by mutableStateOf("")
        private set
    var formEmail by mutableStateOf("")
        private set
    var formSubject by mutableStateOf("")
        private set
    var formMessage by mutableStateOf("")
        private set
    var formResponse by mutableStateOf<ContactFormResponse?>(null)
        private set
    var isSubmitting by mutableStateOf(false)
        private set

    init {
        viewModelScope.launch {
            delay(80)
            contactInfo = repository.getContactInfo()
            isLoading = false
        }
    }

    fun updateName(name: String) { formName = name }
    fun updateEmail(email: String) { formEmail = email }
    fun updateSubject(subject: String) { formSubject = subject }
    fun updateMessage(message: String) { formMessage = message }

    fun submitForm() {
        viewModelScope.launch {
            isSubmitting = true
            val formData = ContactFormData(
                name = formName,
                email = formEmail,
                subject = formSubject,
                message = formMessage
            )
            formResponse = repository.submitContactQuery(formData)
            isSubmitting = false
        }
    }

    fun clearForm() {
        formName = ""
        formEmail = ""
        formSubject = ""
        formMessage = ""
        formResponse = null
    }
}
