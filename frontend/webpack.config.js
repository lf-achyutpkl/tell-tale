var debug = process.env.NODE_ENV !== 'production';

module.exports = debug? require('./webpack.dev.config') : require('./webpack.production.config');
