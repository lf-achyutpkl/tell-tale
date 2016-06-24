var webpack = require('webpack');

module.exports = {
    context: __dirname,
    devtool: 'inline-sourcemap',
    entry: [
        'webpack-hot-middleware/client',
        './src/js/main.js'
    ],
    module: {
        loaders: [
            {
                test: /\.jsx?$/,
                exclude: /(node_modules|bower_components)/,
                loaders: ['react-hot', 'babel-loader']
            }
        ]
    },
    output: {
        path: __dirname + '/public/build',
        publicPath: '/js/',
        filename: 'bundle.js'
    },
    plugins:
        [
            new webpack.optimize.OccurenceOrderPlugin(),
            new webpack.HotModuleReplacementPlugin()
        ]
};
