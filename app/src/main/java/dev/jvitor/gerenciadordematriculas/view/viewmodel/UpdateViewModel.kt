package dev.jvitor.gerenciadordematriculas.view.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import dev.jvitor.gerenciadordematriculas.R
import dev.jvitor.gerenciadordematriculas.model.Aluno
import dev.jvitor.gerenciadordematriculas.model.repository.AlunoRepository

class UpdateViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AlunoRepository(application.applicationContext)

    fun get(cpf: String) : String {
        return repository.get(cpf).cpf
    }

    fun validation(context: Context, aluno: Aluno) : Boolean {
        if (!validateName(aluno.name)) {
            Toast.makeText(context, R.string.textErrorName, Toast.LENGTH_SHORT).show()
            return false
        }
        else if (!validateSport(aluno.sport)) {
            Toast.makeText(context, R.string.textErrorSport, Toast.LENGTH_SHORT).show()
            return false
        }
        else if (!validateDay(aluno.day)) {
            Toast.makeText(context, R.string.textErrorDay, Toast.LENGTH_SHORT).show()
            return false
        }
        else {
            repository.save(aluno)
            Toast.makeText(context, R.string.textSucessUpdated, Toast.LENGTH_SHORT).show()
            return true
        }
    }

    private fun validateName(name: String) =
        (name.isNotBlank() && name.isNotEmpty() && name.length >= 4)

    private fun validateSport(sport: String) =
        (sport.length >= 3 && sport.isNotBlank() && sport.isNotEmpty())

    private fun validateDay(day: String): Boolean {
        day.replace(".", "").replace("-", "").replace(",", "")
        return (day.toInt() in 1..31 && day.isNotEmpty() && day.isNotBlank())
    }


}