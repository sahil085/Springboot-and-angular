
'use strict'
var demoApp = angular.module('demo', ['ui.bootstrap', 'demo.controllers',
    'demo.services','ngRoute'
]);
demoApp.constant("CONSTANTS", {
    isUserLoggedIn : "/isloggedin",
    getUserByIdUrl: "/user/getUser/",
    getAllUsers: "/user/getAllUsers",
    saveUser: "/user/saveUser"
});