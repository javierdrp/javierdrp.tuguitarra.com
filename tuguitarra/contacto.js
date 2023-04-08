let ultimoNombre = '';

async function postContacto(nombre, email, mensaje) {
    try 
    {
        const response = await fetch('http://localhost:8080/api/contacto', 
        {
            method: 'POST',
            headers: 
            {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: 
            `{
                "nombre": "${nombre}",
                "email": "${email}",
                "mensaje": "${mensaje}"
            }`,
        });

        if (response.ok) ultimoNombre = nombre;
        else throw new Error(await response.text());
    } 
    catch (error) 
    {
        return error.message;
    }
    return null;
}

async function enviarContacto()
{
    const erroresContacto = document.getElementById('erroresContacto');

    postContacto(
        document.getElementById('nombre').value,
        document.getElementById('email').value,
        document.getElementById('mensaje').value
    ).then(
        (error) => 
        {
            if(error)
            {
                const listaErrores = document.createElement('ul');
                const errores = error.split(/[\r\n]+/); 
                for(let i = 0; i < errores.length; i++)
                {
                    if(errores[i] != "")
                    {
                        const elemento = document.createElement('li');
                        elemento.textContent = errores[i];
                        listaErrores.appendChild(elemento);
                    }
                }
                erroresContacto.innerHTML = listaErrores.outerHTML;
            }
            else
                erroresContacto.textContent = `Muchas gracias por tu mensaje, ${ultimoNombre}. Nos pondremos en contacto lo antes posible.`;
        }
    )
}