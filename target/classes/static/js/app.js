require.config({
    baseUrl: 'resources/js/lib',
    paths: {
        'css': './css.min',
        'jquery': './jquery-2.1.4.min',
        'bootstrap': './bootstrap.min',
        'pace': './plugins/pace.min',
        'main': './main',
        'app': '../app'
    },
    shim: {
        'bootstrap': {
            'deps': ['jquery']
            //'deps': ['jquery','pace','css!../../css/main.css','css!../../css/font-awesome.css']
        }
    }
});
