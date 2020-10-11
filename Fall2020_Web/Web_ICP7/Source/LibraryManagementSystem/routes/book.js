var express = require('express');
var router = express.Router();
var Book = require('../models/Book.js');

/* GET ALL BOOKS */
router.get('/', function (req, res, next) {
  Book.find(function (err, products) {
    if (err) return next(err);
    res.json(products);
  });
});

/* GET SINGLE BOOK BY ID */
router.get('/:id', function (req, res, next) {
  Book.findById(req.params.id, function (err, post) {
    if (err) return next(err);
    res.json(post);
  });
});

/* SAVE BOOK */
router.post('/', function (req, res, next) {
  Book.create(req.body, function (err, post) {
    if (err) return next(err);
    res.json(post);
  });
});

/* UPDATE BOOK */
// like with GET&SAVE, when an ID matches, you update record
// use code from MongoDB, mLab projects
// instead of $set from demo, using setValue in book-edit.component.ts
router.put('/:id', function (req, res, next) {
  Book.findByIdAndUpdate(req.params.id, req.body, function (err, post) {
    if (err) return next(err);
    res.json(post);
  });
});

/* DELETE BOOK */
// like with GET, when ID matches, you delete record
// use code from MongoDB, mLab projects
router.delete('/:id', function (req, res, next) {
  console.log('Trying to DELETE a book.');

  Book.findByIdAndDelete(req.params.id, function (err, post) {
    if (err){
      //console.log(err.message);
      return next(err);
    }
    res.json(post);
  });
});

module.exports = router;