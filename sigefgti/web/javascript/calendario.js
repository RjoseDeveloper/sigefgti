/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
            var img = new Image();

img.src = 'img/direcao.jpg';
var CanvasXSize = 400;
var CanvasYSize = 200;
var speed = 40; // quanto menor for, mais rapido.
var scale = 1.05;
var y = -4.5; // vertical offset

var dx = 0.75;
var imgW;
var imgH;
var x = 0;
var clearX;
var clearY;
var ctx;

img.onload = function() {
    imgW = img.width * scale;
    imgH = img.height * scale;
    
    if (imgW > CanvasXSize) {
        // imagem mais larga que canvas
        x = CanvasXSize - imgW;
    }
    if (imgW > CanvasXSize) {
        // se a larura for maior que a canvas
        clearX = imgW;
    } else {
        clearX = CanvasXSize;
    }
    if (imgH > CanvasYSize) {
        // altura da imagem larga que a canvas
        clearY = imgH;
    } else {
        clearY = CanvasYSize;
    }
    
    // get do contexto do canva
    ctx = document.getElementById('calendario').getContext('2d');
 
    // taxa de actualizacao
    return setInterval(draw, speed);
};

function draw() {
    ctx.clearRect(0, 0, clearX, clearY); // limpar a canvas
    
    // se imagem é <= Canvas 
    if (imgW <= CanvasXSize) {
        // limpar, comecar do principio
        if (x > CanvasXSize) {
            x = -imgW + x;
        }
        // dsenhar imagem addicional
        if (x > 0) {
            ctx.drawImage(img, -imgW + x, y, imgW, imgH);
        }
        // desenhar imagem adicional 2
        if (x - imgW > 0) {
            ctx.drawImage(img, -imgW * 2 + x, y, imgW, imgH);
        }
    }

    // image is > Canvas Size
    else {
        // reset, start from beginning
        if (x > (CanvasXSize)) {
            x = CanvasXSize - imgW;
        }
        // draw aditional image
        if (x > (CanvasXSize-imgW)) {
            ctx.drawImage(img, x - imgW + 1, y, imgW, imgH);
        }
    }
    // draw image
    ctx.drawImage(img, x, y,imgW, imgH);
    // amount to move
    x += dx;
}
