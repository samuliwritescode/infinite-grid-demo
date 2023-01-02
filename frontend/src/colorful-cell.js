import {LitElement, html} from 'lit';

class ColorfulCell extends LitElement {
  render() {
    return html`
   <style>
            :host {
                display: inline-block;
                width: 100%;
                height: 100%;
                cursor: hand;
            }
        </style>
   <div on-click="onClick" style="cursor: pointer; width: 100%; height: 100%; background-color: [[color]]"></div>
`;
  }

  static get is() {
      return 'colorful-cell'
  }

  static get properties() {
      return {
          x: Number,
          y:Number,
          color: {
              type: String,
              computed: 'getColor(x,y)'
          }
      }
  }

  getColor(x,y) {
      let r = Math.abs(Math.sin(x * 0.3) * 255);
      let g = Math.abs(Math.sin(x * 0.2) * 127 + Math.cos(y * 0.2) * 127);
      let b = Math.abs(Math.cos(y * 0.3) * 255);
      return "rgb("+r+","+g+","+b+")";
  }

  onClick() {
      this.dispatchEvent(new CustomEvent('clickcell', {
          detail: {
              x: this.x,
              y: this.y
          },
          bubbles: true,
          composed: true
      }));
  }
}
customElements.define(ColorfulCell.is, ColorfulCell);

