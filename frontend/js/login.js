async function validarLogin() {
  const nombreUsuario = document.getElementById('icon_user').value;
  const password = document.getElementById('icon_pass').value;

  if (!nombreUsuario || !password) {
    alert("Por favor ingrese usuario y contraseña");
    return;
  }

  try {
    const resp = await fetch(`${API}/auth/login`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ nombreUsuario, password })
    });

    if (!resp.ok) {
      alert("Credenciales inválidas");
      return;
    }

    const data = await resp.json();
    localStorage.setItem("token", data.token);

    alert("Inicio de sesión exitoso ✅");
    window.location = "menu.html";
  } catch (e) {
    console.error(e);
    alert("Error al conectar con el servidor.");
  }
}
