const styleElement = document.createElement('dom-module');
styleElement.innerHTML = `
    <template>
        <style>
            :host(.borders) .cell {
                border-left-style: solid;
                border-bottom-style: solid;
                border-color: lightgray;
                border-left-width: 1px;
                border-bottom-width: 1px;
            }
        </style>
    </template>
        `;
styleElement.register('shared-styles');