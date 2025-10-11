const API = "http://localhost:8064";

/**
 * Función genérica para consumir la API con Fetch y JWT
 */
async function apiFetch(endpoint, options = {}) {
  const token = localStorage.getItem("token");
  const headers = options.headers ? { ...options.headers } : {};

  if (token) headers["Authorization"] = `Bearer ${token}`;
  if (!headers["Content-Type"] && options.body && !(options.body instanceof FormData)) {
    headers["Content-Type"] = "application/json";
  }

  const resp = await fetch(`${API}${endpoint}`, { ...options, headers });

  if (resp.status === 401 || resp.status === 403) {
    alert("Sesión expirada. Inicie sesión nuevamente.");
    localStorage.removeItem("token");
    window.location = "index.html";
    return;
  }

  if (!resp.ok) throw new Error("Error en la solicitud");
  const text = await resp.text();
  return text ? JSON.parse(text) : null;
}

/** Cierra sesión */
function logout() {
  localStorage.removeItem("token");
  window.location = "index.html";
}
