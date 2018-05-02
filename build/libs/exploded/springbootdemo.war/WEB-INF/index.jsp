<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html itemscope itemtype="http://schema.org/Article">
<head>
  <!-- BEGIN Pre-requisites -->
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js">
  </script>
  <script src="https://apis.google.com/js/client:platform.js?onload=start" async defer>
  </script>
  <script>
      function start() {
          gapi.load('auth2', function() {
              auth2 = gapi.auth2.init({
                  client_id: '604858241500-fetrd4pr8t2dp56unpistoeq043ifs49.apps.googleusercontent.com',
                  // Scopes to request in addition to 'profile' and 'email'
                  //scope: 'additional_scope'
              });
          });
      }
  </script>
</head>
<body>
<sec:authorize access="isAuthenticated()">
login hai
  <a href="/logout">logout</a>
</sec:authorize>
<sec:authorize access="isAnonymous()">

<button id="signinButton">Sign in with Google</button>
</sec:authorize>

<script>
    $('#signinButton').click(function() {
        // signInCallback defined in step 6.
        auth2.grantOfflineAccess().then(signInCallback);
    });
</script>
<script>
    function signInCallback(authResult) {
        if (authResult['code']) {

            // Hide the sign-in button now that the user is authorized, for example:
            $('#signinButton').attr('style', 'display: none');

            // Send the code to the server
            $.ajax({
                type: 'POST',
                url: '/token',
                // Always include an `X-Requested-With` header in every AJAX request,
                // to protect against CSRF attacks.
                headers: {
                    'X-Requested-With': 'XMLHttpRequest'
                },
                contentType: 'application/octet-stream; charset=utf-8',
                success: function(result) {
                  alert(result)
                    // Handle or verify the server response.
                },
                processData: false,
                data: authResult['code']
            });
        } else {
            // There was an error.
        }
    }
</script>

</body>
</html>
