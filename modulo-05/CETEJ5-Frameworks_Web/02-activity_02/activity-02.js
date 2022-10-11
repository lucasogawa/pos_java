const $id = function (id) {
    return document.getElementById(id);
};

const updateQnt = function (qnt, noteQnt, noteValue) {
    return qnt - noteQnt * noteValue;
};

document.querySelector('#btn-get-money').addEventListener('click', () => {
    let qnt = $id('quantity').value;

    if (qnt < 10 || qnt > 600) {
        return;
    }

    let qnt100 = parseInt(qnt / 100);
    qnt = updateQnt(qnt, qnt100, 100);

    let qnt50 = parseInt(qnt / 50);
    qnt = updateQnt(qnt, qnt50, 50);

    let qnt20 = parseInt(qnt / 20);
    qnt = updateQnt(qnt, qnt20, 20);

    let qnt10 = parseInt(qnt / 10);
    qnt = updateQnt(qnt, qnt10, 10);

    let qnt5 = parseInt(qnt / 5);
    qnt = updateQnt(qnt, qnt5, 5);

    let qnt2 = parseInt(qnt / 2);
    qnt = updateQnt(qnt, qnt2, 2);

    let qnt1 = parseInt(qnt / 1);
    qnt = updateQnt(qnt, qnt1, 1);

    let result = '';

    if (qnt100 != 0) {
        result += `\nR$ 100 - ${qnt100} notes`;
    }
    if (qnt50 != 0) {
        result += `\nR$ 50   - ${qnt50} notes`;
    }
    if (qnt20 != 0) {
        result += `\nR$ 20   - ${qnt20} notes`;
    }
    if (qnt10 != 0) {
        result += `\nR$ 10   - ${qnt10} notes`;
    }
    if (qnt5 != 0) {
        result += `\nR$ 5     - ${qnt5} notes`;
    }
    if (qnt2 != 0) {
        result += `\nR$ 2     - ${qnt2} notes`;
    }
    if (qnt1 != 0) {
        result += `\nR$ 1     - ${qnt1} notes`;
    }

    window.alert(result);
});
