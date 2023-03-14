import { ref } from "vue";
import axios from "axios";

class EventsService {
  events;

  constructor() {
    this.events = ref([]);
  }

  getEvents() {
    return this.events;
  }

  async fetchAll() {
    try {
      await axios.get("http://localhost:8080/api/events").then((response) => {
        this.events.value = response.data;
      });
    } catch (err) {}
  }

  async deleteEvent(props, rows) {
    try {
      axios
        .delete("/" + props.id)
        .then((res) => {
          const index = rows.value.findIndex((row) => row.id === props.id);
          rows.value.splice(index, 1);
        });
    } catch (err) {
      console.log(err);
    }
  }
}

export default EventsService;
