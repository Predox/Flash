from playwright.sync_api import sync_playwright, expect
import time
import random
import string

with sync_playwright() as p:
    browser = p.chromium.launch(headless=False)
    page = browser.new_page(
        base_url='http://localhost:8080/'
    )
    page.goto('home.html')
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
    page.click('#btnSalvarPreset')
    time.sleep(1)
    if select_locator.count() == (quantidade + 1):
        print("Preset Adicionado")
        time.sleep(1)
    else:
        print("Problema ao adicionar Preset")
    if select_locator.nth(quantidade).text_content() == "teste":
        page.locator("#presets").select_option(index=quantidade)
        time.sleep(1)
        print("Preset Foi Cadastrado e Encontrado\n")
        time.sleep(1)
        page.once("dialog", lambda dialog: dialog.accept())
        page.click('#deletarPreset')
        time.sleep(1)
        print("Preset deletado\n")



    
   