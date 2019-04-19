const http = (function () {
    const send = (url, method, body) => {
        console.log("body", body);
        console.log("method", method);
        console.log("url", url);
        const payload = {
            headers: {
                "Content-Type": "application/json",
                // "Content-Type": "application/x-www-form-urlencoded",
                'Accept': 'application/json',
                "X-CSRF-TOKEN": $("input[name=_csrf]").val()
            },
             method,
            
            body: JSON.stringify(body)
        };
        return fetch(url, payload)
            .then(response => response.json()).catch(er=> console.log("err", er));
    };
    const post = (url, body) => send(url, 'POST', body);
    const get = (url) => send(url, 'GET', null);
    
    return {
        send,
        post,
        get
    };
}());



