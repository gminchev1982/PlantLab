const http = (function () {
    const send = (url, method, body) =
>
    {
        let payload = {
            headers: {
                "Content-Type": "application/json",
                // "Content-Type": "application/x-www-form-urlencoded",
                'Accept': 'application/json',
                "X-CSRF-TOKEN": $("input[name=_csrf]").val()
            },
            method
        }
        if (method === "POST") {
            payload.body = JSON.stringify(body);
        }

        console.log("payload", payload)
        return fetch(url, payload)
            .then(response = > response
    )
    .
        catch(er = > console.log("err", er)
    )
        ;
    }
    ;
    const post = (url, body) =
>
    send(url, 'POST', body);
    const get = (url) =
>
    send(url, 'GET', null);

    return {
        send,
        post,
        get
    };
}());

const responseNotes = (path, id) =
>
{

    const form = $("#" + id).serializeArray()
    let data = {};
    $(form).each(function (index, obj) {
        data[obj.name] = obj.value;
    });

    http.post("/api/" + path, data)
        .then((data) = > {

        const response = data.json();

    $('.alert').html('');
    if (data.status === 400) {
        response.then(resp = > {

            resp.forEach((err) = > $('.alert').append(`<div>${err.code}</div>`)
    )
        ;
        $('.alert').removeClass('d-none');
        $('.alert').removeClass('alert-success');
        $('.alert').addClass('alert-danger').show();
    })
        ;
    } else {
        response.then(resp = > {
            $('.alert'
    ).
        html(resp);
        $('.alert').removeClass('d-none');
        $('.alert').removeClass('alert-danger');
        $('.alert').addClass('alert-success');
    })
    }
})
    ;
}

const responseNotFound = (path, response) =
>
{
    response.then(resp = > {
        $('.alert'
).
    html(resp.message);
    $('.alert').removeClass('d-none');
    $('.alert').removeClass('alert-success');
    $('.alert').addClass('alert-danger').show();
    $('.alert').removeClass('d-none');
    $("#" + path).hide();
})
    ;
}

