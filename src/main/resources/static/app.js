function obtenerNumeros() {
	fetch('/numeros')
		.then(response => response.json())
		.then(data => {
			const contenedor = document.getElementById('numeros');
			contenedor.innerHTML = '';
			data.forEach(num => {
				const div = document.createElement('div');
				div.textContent = num;
				contenedor.appendChild(div);
			});
		})
		.catch(error => {
			document.getElementById('numeros').innerHTML = 'Error al obtener los números.';
		});
}


function consumirApiExterna() {
	fetch('https://jsonplaceholder.typicode.com/users')
		.then(response => response.json())
		.then(data => {
			const contenedor = document.getElementById('api-externa');
			contenedor.innerHTML = '<h3>Usuarios de API externa:</h3>';
			data.forEach(usuario => {
				const div = document.createElement('div');
				div.textContent = usuario.name + ' (' + usuario.email + ')';
				contenedor.appendChild(div);
			});
		})
		.catch(error => {
			document.getElementById('api-externa').innerHTML = 'Error al consumir la API externa.';
		});
}

function procesar() {
	fetch('/procesar')
		.then(response => response.json())
		.then(data => {
			const contenedor = document.getElementById('procesar');
			contenedor.innerHTML = '<h3>Nombres:</h3>';
			data.forEach(element => {
				const div = document.createElement('div');
				div.textContent = element;
				contenedor.appendChild(div);
			});
		})
		.catch(error => {
			document.getElementById('procesar').innerHTML = 'Error al consumir la API externa.';
		});
}

function enviarDatosApiExterna(event) {
	event.preventDefault();
	const nombre = document.getElementById('nombre').value;
	const email = document.getElementById('email').value;
	const datos = {
		name: nombre,
		email: email
	};

	fetch('https://jsonplaceholder.typicode.com/users', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(datos)
	})
	.then(response => response.json())
	.then(data => {
		const contenedor = document.getElementById('post-externa');
		contenedor.innerHTML = '<h3>Respuesta del POST:</h3>' + JSON.stringify(data, null, 2);
	})
	.catch(error => {
		document.getElementById('post-externa').innerHTML = 'Error al enviar datos a la API externa.';
	});
}

function mostrarBuffer() {
    fetch('/buffer')
        .then(response => response.json())
        .then(data => {
            const contenedor = document.getElementById('buffer');
            contenedor.innerHTML = '<h3>Buffer consumido:</h3>';
            data.forEach(num => {
                const div = document.createElement('div');
                div.textContent = num;
                contenedor.appendChild(div);
            });
        })
        .catch(error => {
            document.getElementById('buffer').innerHTML = 'Error al ejecutar el ejemplo de concurrencia.';
        });
}