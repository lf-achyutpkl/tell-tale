;(function () {
  'use strict';
  var gulp = require('gulp');
  var source = require('vinyl-source-stream');
  var gulpif = require('gulp-if');
  var notify = require('gulp-notify');
  var uglify = require('gulp-uglify');
  var gutil = require('gulp-util');

  var inject = require('gulp-inject');
  var imagemin = require('gulp-imagemin');
  var iconfont = require('gulp-iconfont');
  var cssnano = require('gulp-cssnano');
  var concat = require('gulp-concat');
  var htmlreplace = require('gulp-html-replace');

  //webpack essentials
  var webpack = require('webpack');
  var webpackStream = require('webpack-stream');
  var webpackConfig = require('./webpack.config.js');
  var webpackDevMiddleware = require('webpack-dev-middleware');
  var webpackHotMiddleware = require('webpack-hot-middleware');

  //server components
  var browserSync = require('browser-sync');
  var historyApiFallback = require('connect-history-api-fallback');

  //variable declaration
  var bundler = webpack(webpackConfig);
  var url = require('url');

  var config = {
    paths: {
      src: {
        js: './src/js/**/*.js*',
        css: [
          './src/css/bootstrap.min.css',
          './src/css/stylish-portfolio.css',
          './src/css/rotate-word.css',
          './src/css/remodal-default-theme.css',
          './src/css/font-awesome.css',
          './src/css/glyphicons.css',
          './src/css/forms.css',
          './src/css/fontface.css',
          './src/css/custom.css',
          'node_modules/react-select/dist/react-select.css',
          'node_modules/toastr/build/toastr.css',
          'node_modules/jquery-confirm/dist/jquery-confirm.min.css',
          './src/css/page-not-found.css',
          './src/css/main.css'
        ],
        img: ['./src/img/*', './src/img/**/*'],
        fonts: './src/css/fonts/*',
        vendorJs: [
          'node_modules/jquery/dist/jquery.min.js',
          './src/custom-ui/bootstrap.js',
          './src/custom-ui/plugins.js',
          './src/custom-ui/app.js',
          './src/custom-ui/remodal.js',
          'node_modules/jquery-confirm/dist/jquery-confirm.min.js'
        ]
      },
      dist: {
        js: './dist/js',
        css: './dist/css',
        img: './dist/img',
        fonts: './dist/css/fonts'
      },
      appJs: './src/js/app.js',
      html: './index.html'
    },
    env: {
      development: 'development'
    }
  };

  //environment
  var env = process.env.NODE_ENV || config.env.development;
  var isProduction = process.env.NODE_ENV === 'production';

  //Styles task
  gulp.task('styles', function () {
    gulp.src(config.paths.src.css)
      .pipe(concat('final.css'))
      .pipe(gulpif(isProduction, cssnano()).on('error', gutil.log))
      .pipe(gulp.dest(config.paths.dist.css));
  });

  //Scripts task using webpack
  gulp.task('scripts', function () {
    return gulp.src(config.paths.appJs)
      .pipe(webpackStream(webpackConfig))
      .pipe(gulp.dest(config.paths.dist.js))
      .pipe(gulpif(isProduction, uglify()).on('error', gutil.log))
      .pipe(gulpif(!isProduction, notify('Scripts compiled')));
  });

  /*
   Browser Sync
   */
  gulp.task('browser-sync', function () {
    var proxy = require('proxy-middleware');
    var proxyOptions = url.parse('http://10.10.11.223:8080/tell-tale');
    proxyOptions.route = '/tell-tale';
    browserSync({
      server: {
        baseDir: 'dist',
        middleware: [
          historyApiFallback(),
          proxy(proxyOptions),
          webpackDevMiddleware(bundler, {
            publicPath: webpackConfig.output.publicPath,
            stats: {colors: true}
          }),
          webpackHotMiddleware(bundler)
        ]
      },
      ghostMode: false
    })
  });

  //Images task
  gulp.task('images', function () {
    gulp.src(config.paths.src.img)
      .pipe(gulpif(isProduction, imagemin({
        progressive: true,
        interlaced: true,
        svgoPlugins: [
          {removeViewBox: false},
          {cleanupIDs: false}
        ]
      })))
      .pipe(gulp.dest(config.paths.dist.img));
  });

  gulp.task('vendorJs', function () {
    // Compiles CSS
    gulp.src(config.paths.src.vendorJs)
      .pipe(concat('vendor.min.js'))
      .pipe(gulp.dest(config.paths.dist.js))
  });


  gulp.task('fonts', function () {
    return gulp.src(config.paths.src.fonts)
      .pipe(iconfont({
        fontName: 'myfont',
        prependUnicode: true,
        formats: ['ttf', 'eot']
      }))
      .pipe(gulp.dest(config.paths.dist.fonts));
  });

  gulp.task('html', ['scripts', 'styles', 'vendorJs'], function () {
    var sources = gulp.src(['./dist/js/vendor.min.js', './dist/js/bundle.js', './dist/css/final.css'], {read: false});
    gulp.src('./index.html')
      .pipe(inject(sources, {
        ignorePath: 'dist',
        addRootSlash: false
      }))
      .pipe(htmlreplace({
        'base': '<base href=\"' + '/' + '\">'
      }))
      .pipe(gulp.dest('./dist'))
  });


  gulp.task('watch', function () {
    gulp.watch(config.paths.src.css, ['styles']); // gulp watch for css changes
    gulp.watch(config.paths.src.js, ['scripts']); // gulp watch for js changes
    gulp.watch(config.paths.src.img, ['images']); // gulp watch for js changes
    gulp.watch(config.paths.src.fonts, ['fonts']); // gulp watch for js changes
  });

  gulp.task('default', [
      'styles',
      'scripts',
      'vendorJs',
      'browser-sync',
      'images',
      'fonts',
      'watch',
      'html'
    ]
  );

  gulp.task('build', [
    'styles',
    'scripts',
    'vendorJs',
    'images',
    'fonts',
    'html'
  ]);

})();