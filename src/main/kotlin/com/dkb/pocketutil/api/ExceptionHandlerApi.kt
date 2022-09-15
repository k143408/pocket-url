package com.dkb.pocketutil.api

import com.dkb.pocketutil.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandlerApi {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): MutableMap<String, String?> {
        val errors = mutableMapOf<String, String?>()
        ex.bindingResult.allErrors.forEach { error ->
            val fieldName: String = (error as FieldError).field
            val errorMessage: String? = error.defaultMessage
            errors[fieldName] = errorMessage
        }
        return errors
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun handleValidationExceptions(
        ex: NotFoundException,
        request: HttpServletRequest
    ): MutableMap<String, String?> {
        val errors = mutableMapOf<String, String?>()
        errors["message"] = request.requestURL.append(" is not found").toString()
        return errors
    }
}
