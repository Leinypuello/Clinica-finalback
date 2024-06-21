const tableBody = document.querySelector("#odontologosTable tbody");
const apiURL = "http://localhost:8080";

// modificar un odontologo
var editar = function editOdontologo(id, nombre, apellido, numeroMatricula) {
  document.getElementById('editId').value = id;
  document.getElementById('editNombre').value = nombre;
  document.getElementById('editApellido').value = apellido;
  document.getElementById('editNumeroMatricula').value = numeroMatricula;
  var editModal = new bootstrap.Modal(document.getElementById('editModal'));
  editModal.show();
}

const editForm = document.getElementById("editForm");

editForm.addEventListener("submit", function (event) {
  event.preventDefault();

  const id = document.getElementById("editId").value;
  const nombre = document.getElementById("editNombre").value;
  const apellido = document.getElementById("editApellido").value;
  const numeroMatricula = document.getElementById("editNumeroMatricula").value;

  fetch(`${apiURL}/odontologo/actualizar`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({id ,nombre, apellido, numeroMatricula }),
  })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      alert("Odontólogo actualizado con éxito");
      
    })
    .catch((error) => {
      console.error("Error actualizando odontólogo:", error);
    });
});

 // eliminar un odontologo
var eliminar = function deleteOdontologo(id) {
  document.getElementById('editId').value = id;
  if (confirm("¿Estás seguro de que deseas eliminar este odontólogo?")) {
      
      fetch(`${apiURL}/odontologo/eliminar/${id}`, {
          method: "DELETE",
      })
      .then((response) => {
          if (!response.ok) {
              throw new Error("Error al eliminar el odontólogo");
          }
          return response.json();
      })
      .then((data) => {
          console.log(data);
          alert("Odontólogo eliminado correctamente");
          fetchOdontologos(); // Volver a cargar la lista de odontólogos actualizada
      })
      .catch((error) => {
          console.error("Error eliminando odontólogo:", error);
          alert("Error al eliminar el odontólogo");
      });
  }
}
function fetchOdontologos() {
  // listando los odontologos

  fetch(`${apiURL}/odontologo/listar`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      // Limpiar el contenido actual de la tabla
      tableBody.innerHTML = "";

      // Insertar los datos en la tabla
      data.forEach((odontologo, index) => {
        const row = document.createElement("tr");

        row.innerHTML = `
                <td>${odontologo.id}</td>
                <td>${odontologo.nombre}</td>
                <td>${odontologo.apellido}</td>
                <td>${odontologo.numeroMatricula}</td>
                <td>
                  <button class="btn btn-primary btn-sm" onclick="editar(${odontologo.id}, '${odontologo.nombre}', '${odontologo.apellido}', '${odontologo.numeroMatricula}')">Modificar</button>
                  <button class="btn btn-danger btn-sm" onclick="eliminar(${odontologo.id})">Eliminar</button>
                </td>
              `;

        tableBody.appendChild(row);
      });
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
}

fetchOdontologos();