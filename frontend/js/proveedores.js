async function loadData() {
  try {
    const data = await apiFetch("/proveedores/list");
    const table = document.getElementById('proveedor-table');
    table.innerHTML = (data || []).map(p => `
      <tr>
        <td>${p.id}</td>
        <td>${p.nit}</td>
        <td>${p.nombre}</td>
        <td>${p.ciudad}</td>
        <td>${p.telefono}</td>
        <td>${p.direccion}</td>
        <td>
          <button class="btn btn-sm btn-primary"
            onclick="window.location='form_proveedores.html?idproveedor=${p.id}'">Editar</button>
        </td>
      </tr>
    `).join('');
  } catch (e) {
    console.error(e);
    document.getElementById('proveedor-table').innerHTML =
      `<tr><td colspan="7">Error al recuperar los datos.</td></tr>`;
  }
}

async function loadProveedor(id) {
  try {
    const p = await apiFetch(`/proveedores/list/${id}`);
    document.getElementById('proveedor-id').value = p.id;
    document.getElementById('proveedor-nit').value = p.nit;
    document.getElementById('proveedor-nombre').value = p.nombre;
    document.getElementById('proveedor-ciudad').value = p.ciudad;
    document.getElementById('proveedor-telefono').value = p.telefono;
    document.getElementById('proveedor-direccion').value = p.direccion;
  } catch {
    alert("Error al recuperar proveedor.");
  }
}

async function saveProveedor() {
  const data = {
    id: document.getElementById('proveedor-id').value,
    nit: document.getElementById('proveedor-nit').value,
    nombre: document.getElementById('proveedor-nombre').value,
    ciudad: document.getElementById('proveedor-ciudad').value,
    telefono: document.getElementById('proveedor-telefono').value,
    direccion: document.getElementById('proveedor-direccion').value
  };
  const method = data.id ? "PUT" : "POST";

  try {
    await apiFetch("/proveedores/", { method, body: JSON.stringify(data) });
    alert("Proveedor guardado correctamente ✅");
    window.location = "proveedores.html";
  } catch {
    alert("Error al guardar proveedor.");
  }
}

async function deleteProveedor() {
  const id = document.getElementById('proveedor-id').value;
  if (!id) return alert("No hay proveedor seleccionado.");
  if (!confirm("¿Seguro que deseas eliminarlo?")) return;

  try {
    await apiFetch(`/proveedores/${id}`, { method: "DELETE" });
    alert("Proveedor eliminado ✅");
    window.location = "proveedores.html";
  } catch {
    alert("Error al eliminar proveedor.");
  }
}
