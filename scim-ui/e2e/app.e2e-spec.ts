import { ScimUiPage } from './app.po';

describe('scim-ui App', () => {
  let page: ScimUiPage;

  beforeEach(() => {
    page = new ScimUiPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
