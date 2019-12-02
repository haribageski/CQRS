package event_module

trait EventListener {

  def handle(event: Event): Unit

}
