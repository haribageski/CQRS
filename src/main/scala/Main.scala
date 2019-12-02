import command_module.Commands.InsertPatientCommand
import command_module.PatientEntity

object Main extends App {
  val command = InsertPatientCommand(PatientEntity("123"))
  command.run()
}
