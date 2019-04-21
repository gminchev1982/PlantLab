const http = (function () {
    const send = (url, method, body) => {
        console.log("body", body);
        console.log("method", method);
        console.log("url", url);
        let payload
        if (method==="GET") {
            payload = {
                headers: {
                    "Content-Type": "application/json",
                    // "Content-Type": "application/x-www-form-urlencoded",
                    'Accept': 'application/json',
                    "X-CSRF-TOKEN": $("input[name=_csrf]").val()
                },
                method
            }
        }else {
            payload = {
                headers: {
                    "Content-Type": "application/json",
                    // "Content-Type": "application/x-www-form-urlencoded",
                    'Accept': 'application/json',
                    "X-CSRF-TOKEN": $("input[name=_csrf]").val()
                },
                method,
                body: JSON.stringify(body)
            };
        }
        console.log("payload", payload)
        return fetch(url, payload)
            .then(response => response).catch(er=> console.log("err", er));
    };
    const post = (url, body) => send(url, 'POST', body);
    const get = (url) => send(url, 'GET', null);
    
    return {
        send,
        post,
        get
    };
}());



