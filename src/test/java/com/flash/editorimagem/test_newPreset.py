from playwright.sync_api import sync_playwright, expect
import time

with sync_playwright() as p:
    browser = p.chromium.launch(headless=False)
    page = browser.new_page(
        base_url='http://localhost:8080/'
    )
    page.goto('index.html')
    page.fill('#email', 'pedro@example.com')
    page.fill('#senha', '1234')
    page.click('button[type="submit"]')
    try:
        expect(page).to_have_url('http://localhost:8080/home.html')
        print("Login feito com sucesso!\n")
    except Exception:
        mensagem = page.locator('#mensagemLogin')
        try:
            if mensagem.text_content() == "Email ou senha inválidos.":
                print("Login Incorreto\n")
            else:
                print(f"Mensagem inesperada: {mensagem.text_content()}")
        except:
            print("Login falhou, mas sem mensagem clara de erro\n")
    time.sleep(1)
    select_locator = page.locator('#presets option')
    quantidade = select_locator.count()
    page.locator('#saturacao').evaluate("el => el.value = 25")
    page.locator('#exposicao').evaluate("el => el.value = 25")
    page.locator('#contraste').evaluate("el => el.value = 25")
    page.locator('#realce').evaluate("el => el.value = 25")
    page.locator('#sombras').evaluate("el => el.value = 25")
    page.locator('#brancos').evaluate("el => el.value = 67")
    page.locator('#pretos').evaluate("el => el.value = 67")
    page.locator('#textura').evaluate("el => el.value = 25")
    page.fill("#presetNome","teste")
    time.sleep(1)
    page.click('#btnSalvarPreset')
    time.sleep(1)
    nova_quantidade = select_locator.count()
    time.sleep(1)
    if nova_quantidade == (quantidade + 1):
        print("Preset Adicionado\n")
    else:
        print("Problema ao adicionar Preset\n")
    if select_locator.nth(quantidade).text_content() == "teste":
        page.locator("#presets").select_option(index=quantidade)
        time.sleep(1)
        print("Preset Foi Cadastrado e Encontrado\n")
        time.sleep(1)
        page.once("dialog", lambda dialog: dialog.accept())
        page.click('#deletarPreset')
        time.sleep(1)
        print("Preset deletado\n")



    
   