<script setup>
import { ref, defineProps, reactive } from "vue";
import axios from "axios";

const selected = ref([]);

const props = defineProps({
  event: {
    type: Object,
  }
  // el: '#imagenes',
  // data: {
  //   // checkedNames: []
  //   return:
  //      [
  //       { url: "https://cdn.quasar.dev/img/avatar.png" }
  //     ]
  // }
});

const columns = [
  {
    name: "title",
    required: true,
    label: "Title",
    field: "title",
    align: "center",
    sortable: true,
  },
  { name: "MusicStyle", align: "left", label: "Music Style", field: "musicStyle" },
  // {
  //   name: "rows",
  //   required: true,
  //   label: "rows",
  //   field: "rows",
  //   align: "center",
  //   sortable: true,
  // },
  {
    name: "Description",
    align: "center",
    label: "Description",
    field: "description",
    sortable: true,
  },
  {
    name: "date_hour",
    align: "center",
    label: "Date",
    field: "date_hour",
    sortable: true,
  },
  {
    name: "max_participants",
    align: "center",
    label: "Capacity",
    field: "max_participants",
    sortable: true,
  },
  { name: "Actions", align: "left", label: "Actions", field: "Actions" },
  
];

function getSelectedString(rows) {
  return selected.value.length === 0
  ? ""
  : `${selected.value.length} record${
    selected.value.length > 1 ? "s" : ""
  } selected of ${rows}`;
};

const deleteEventRow = async (props, rows) =>  {

    //   // try {
    //   //   axios
    //   //     .delete("http://localhost:9000/api/events/" + props.id)
    //   //     .then((res) => {
    //   //       const index = rows.value.findIndex((row) => row.id === props.id);
    //   //       rows.value.splice(index, 1);
    // });
    console.log(props);
        // } catch (err) {
        //   console.log(err);
        // }
}


</script>

<template>
  <div class="list q-pa-md">
  <div id="example-3">
    <q-table
      class="bg-grey-4"
      :rows="props.event"
      :columns="columns"
      row-key="name"
      :selected-rows-label="getSelectedString"
      selection="multiple"
      v-model:selected="selected"
    >

    <template #body-cell-#body-cell-MusicStyle>
        <q-td align="center">
    
            <q-img src="http://localhost:8080/rock.png"></q-img>
          
        </q-td>
      </template>

      <!-- <template #body-cell-rows>
        <q-img src="http://localhost:8080/rock.png" />
      </template> -->

      <template #body-cell-Actions>
        <q-td align="center">
          <q-btn
            push
            color="white"
            text-color="primary"
            label="Edit"
            class="q-mr-md"
          />
          <q-btn push color="red" label="delete" @click="deleteEventRow(props.event)" />
          <!-- <q-btn push color="red" text-color="primary" label="add" /> -->
        </q-td>
      </template>

      <template v-slot:header-selection="scope">
        <q-toggle v-model="scope.selected" />
      </template>

      <template v-slot:body-selection="scope">
        <q-toggle v-model="scope.selected" />
      </template>
    </q-table>
  </div>
</div>
</template>
<style lang="scss">
.q-pa-md {
  width: 80vw;
  .q-table {
    thead {
      background-color: #d9d9d9;
    }
    th {
      text-align: center;
      font-size: 1.5em;
    }
    tbody {
      background-color: #b7b7b7;

      td {
        overflow-x: auto;
        font-size: 1.2em;
        font-weight: 500;
        &:nth-child(2) {
          background-color: #9e0404;
          color: white;
          max-width: 10em;
          white-space: pre-wrap;
        }
        &:nth-child(3) {
          white-space: pre-wrap;
          max-width: 20em;
        }
        & {
          overflow-x: auto;
        }

        &::-webkit-scrollbar {
          height: 6px; /* Altura de la barra */
        }

        &::-webkit-scrollbar-thumb {
          background-color: #999; /* Color de la barra */
        }

        &::-webkit-scrollbar-track {
          background-color: #ddd; /* Color del fondo de la barra */
        }
      }
    }
  }
}
.q-img {
  height: 15vh;
  width: 15vw;
}
</style>
