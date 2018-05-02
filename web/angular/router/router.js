demoApp.config(function ($routeProvider) {
    console.log("router");
    $routeProvider.when("   /loginpage", {
            title: "login",
            templateUrl: "angular/Components/loginregistration/loginandregistration.html"
        })


});


// demoApp.run(['$rootScope', '$location', 'appService', '$window', '$http', '$cookies', '$route',
//     function ($rootScope, $location, appService, $window, $http, $cookies, $route) {
//
//         $rootScope.$on('$routeChangeStart', function (event) {
//
//             if (!appService.isLoggedIn() && !$location.path().startsWith("/access_token")) {
//                 event.preventDefault();
//                 $window.location.assign(sfAuthUrl);
//             }
//             else {
//
//                 $http.defaults.headers.common['X-ACCESS-TOKEN'] = appService.getAuthKey();
//
//                 if ($location.path() != "/login") {
//
//                     if (appService.isLoggedIn()) {
//                         $http.get(host + '/login/sfResponse/validated')
//                             .success(function (response) {
//                                 if (response.result == 'VALID') {
//
//                                     if ($rootScope.loggedUser == undefined) {
//                                         $http.get(host + '/ttn/employee/findOne')
//                                             .success(function (empResponse) {
//                                                 $rootScope.loggedUser = empResponse;
//                                                 $rootScope.loggedUser.currentRole = $rootScope.loggedUser.roles[0];
//                                                 //$route.reload();
//                                             });
//                                     }
//                                 }
//                                 else {
//                                     $cookies.remove('__bbusrtoken');
//                                     $window.location.assign(sfAuthUrl);
//                                 }
//                             })
//                             .error(function () {
//                                 $cookies.remove('__bbusrtoken');
//                                 $window.location.assign(sfAuthUrl);
//                             });
//                     }
//                 }
//             }
//
//         });
//     }]);